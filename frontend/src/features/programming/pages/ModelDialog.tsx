import {useEffect,useRef} from 'react';
import type {ModelResult} from '../dto/program';
export function ModelDialog({model,onClose}:{model:ModelResult|null;onClose:()=>void}){
 const dialog=useRef<HTMLDialogElement>(null);
 useEffect(()=>{dialog.current?.showModal();},[]);
 return <dialog ref={dialog} className="model-dialog" aria-labelledby="model-dialog-title" onClose={onClose}><div className="panel-heading"><h2 id="model-dialog-title">Cómo se representa mi programa</h2><button onClick={()=>dialog.current?.close()}>Cerrar</button></div><p>Modelo EMF generado por el backend a partir de tus bloques.</p>{model ? <><p>{model.valid?'Modelo válido':'Revisa el modelo'}</p>{model.diagnostics.map((d,i)=><p key={i}>{d.message}</p>)}<pre tabIndex={0}>{model.xmi ?? 'Sin representación disponible.'}</pre></> : <p role="status">Generando representación…</p>}</dialog>;
}
