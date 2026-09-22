"""Revisión estática del scaffolding; no sustituye Maven, npm ni Docker.

Uso desde la raíz: python scripts/check-scaffold.py
Solo lee archivos; escribe docs/verificacion-estatica.json con resultados.
"""

import hashlib
import json
import os
from pathlib import Path
import re
import xml.etree.ElementTree as ET


ROOT = Path(__file__).resolve().parents[1]
REPORT_FILE = ROOT / "docs/verificacion-estatica.json"
EXCLUDED = {"node_modules", "target", "dist", ".git", ".codex", ".agents", "__pycache__"}
failures: list[str] = []
counts = {"json": 0, "xml": 0, "markdown_links": 0, "java_package_paths": 0}


def relative(path: Path) -> str:
    return path.relative_to(ROOT).as_posix()


for directory, subdirectories, filenames in os.walk(ROOT):
    subdirectories[:] = [name for name in subdirectories if name not in EXCLUDED]
    for filename in filenames:
        path = Path(directory) / filename
        if path.name == "verificacion-estatica.json":
            continue
        if path.suffix not in {".json", ".xml", ".svg", ".md", ".java"}:
            continue
        try:
            source = path.read_text(encoding="utf-8")
            if path.suffix == ".json":
                json.loads(source)
                counts["json"] += 1
            elif path.suffix in {".xml", ".svg"}:
                ET.fromstring(source)
                counts["xml"] += 1
            elif path.suffix == ".md":
                for match in re.finditer(r"\[[^\]]+\]\(([^)]+)\)", source):
                    target = match.group(1).split("#", 1)[0]
                    if not target or re.match(r"^[a-zA-Z]+:", target):
                        continue
                    counts["markdown_links"] += 1
                    linked_path = (path.parent / target).resolve()
                    # Este informe se crea al terminar esta misma ejecución.
                    if linked_path != REPORT_FILE and not linked_path.exists():
                        failures.append(f"{relative(path)}: enlace inexistente {target}")
            elif path.suffix == ".java":
                package = re.search(r"^package\s+([\w.]+);", source, re.MULTILINE)
                if not package:
                    failures.append(f"{relative(path)}: declaración package ausente")
                else:
                    expected = package.group(1).replace(".", "/")
                    if not path.parent.as_posix().endswith("/" + expected):
                        failures.append(f"{relative(path)}: package no coincide con ruta")
                    counts["java_package_paths"] += 1
        except (UnicodeError, ValueError, ET.ParseError) as error:
            failures.append(f"{relative(path)}: {error}")

reference = ROOT / "docs/referencias/marco_conceptual_iu_adaptativa_programacion.svg"
expected_hash = "fcb04a1bf5bbd8bf1b5bb4ca4a40bf931f3f997918bf245a16208e9b5b23f6c9"
# SHA-256 del SVG original adjunto, leído antes de crear la copia de referencia.
reference_hash = hashlib.sha256(reference.read_bytes()).hexdigest()
if reference_hash != expected_hash:
    failures.append("La copia SVG no coincide con el hash del original proporcionado")

report = {
    "kind": "revision_estatica_no_runtime",
    "checked": counts,
    "reference_svg_sha256": reference_hash,
    "failures": failures,
    "passed": not failures,
    "not_verified": [
        "resolucion de dependencias npm/Maven",
        "compilacion Java/TypeScript",
        "pruebas unitarias, integracion y E2E",
        "sintaxis/ejecucion Docker Compose y arranque de servicios",
        "conformidad o transformaciones MDE",
    ],
}
REPORT_FILE.write_text(
    json.dumps(report, ensure_ascii=False, indent=2) + "\n", encoding="utf-8"
)
print(json.dumps(report, ensure_ascii=False, indent=2))
raise SystemExit(1 if failures else 0)
