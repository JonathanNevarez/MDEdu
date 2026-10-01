import * as Blockly from 'blockly/core';

export const STORAGE_KEY = 'mdedu.blockly.workspace.v1';
export function saveWorkspace(workspace: Blockly.Workspace, programName: string, storage: Storage = localStorage) {
  storage.setItem(STORAGE_KEY, JSON.stringify({ version: 1, programName, workspace: Blockly.serialization.workspaces.save(workspace) }));
}
export function restoreWorkspace(workspace: Blockly.Workspace, storage: Storage = localStorage): string {
  const raw = storage.getItem(STORAGE_KEY);
  if (!raw) throw new Error('No hay un workspace guardado.');
  const data: unknown = JSON.parse(raw);
  if (!data || typeof data !== 'object' || !('version' in data) || data.version !== 1 ||
      !('programName' in data) || typeof data.programName !== 'string' ||
      !('workspace' in data) || !data.workspace || typeof data.workspace !== 'object') {
    throw new Error('El workspace guardado no tiene un formato compatible.');
  }
  // Validate in an isolated headless workspace before replacing the visible one.
  const probe = new Blockly.Workspace();
  try { Blockly.serialization.workspaces.load(data.workspace, probe); }
  finally { probe.dispose(); }
  const previous = Blockly.serialization.workspaces.save(workspace);
  try { Blockly.serialization.workspaces.load(data.workspace, workspace); }
  catch (e) { Blockly.serialization.workspaces.load(previous, workspace); throw e; }
  return data.programName;
}
