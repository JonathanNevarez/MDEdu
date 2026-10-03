import { afterEach, beforeEach, expect, it, vi } from 'vitest';
import { cleanup, fireEvent, render, screen, within } from '@testing-library/react';
import { MetaUiPage, TimelineView } from '../../src/features/metaui/MetaUiPage';
import type { Timeline, Overview } from '../../src/features/metaui/types';
import { metaUiApi } from '../../src/features/metaui/metaUiApi';
vi.mock('../../src/features/metaui/metaUiApi', () => ({metaUiApi: {capabilities: vi.fn(), students: vi.fn(), overview: vi.fn(), attempts: vi.fn(), adaptations: vi.fn(), rules: vi.fn(), parameters: vi.fn(), timeline: vi.fn()}}));
const overview=(id: string): Overview => ({studentId:id,modelVersion:1,lastUpdated:'2026-10-02',conceptMasteries:[{conceptId:id,masteryScore:.25,attemptCount:2,successCount:1,failureCount:1,consecutiveFailures:1,averageResolutionTime:20,hintCount:0,recentErrorPatterns:[],lastUpdated:'2026-10-02'}],concepts:[],progress:{levels:[]}});
const trace=(): Timeline => ({studentId:'A',attemptId:'a1',sessionId:null,startedAt:'2026-10-02',completedAt:'2026-10-02',traceStatus:'COMPLETE',gaps:[],attempt:{activityId:'LOOPS',conceptId:'LOOPS',functionalPassed:true,activityPassed:false,masteryBefore:0,masteryDelta:0,masteryAfter:0,policyVersion:1,patterns:[],programHash:'hash'},events:[],activityOpenEvents:[],adaptation:null,feedback:[],uiConfigurations:[]});
beforeEach(() => {
 vi.mocked(metaUiApi.capabilities).mockResolvedValue({canViewStudentModel:true,canViewRules:true,canViewParameters:true,canViewTrace:true,canMutateRules:false,canMutateParameters:false,canReviewProposals:false});
 vi.mocked(metaUiApi.students).mockResolvedValue({items:['A','B'].map(studentId=>({studentId,createdAt:'now',lastInteraction:'now',attemptCount:0,completedConceptCount:0})),page:0,size:20,total:2});
 vi.mocked(metaUiApi.overview).mockImplementation(async id=>overview(id));
 vi.mocked(metaUiApi.attempts).mockResolvedValue({items:[],page:0,size:20,total:0});
 vi.mocked(metaUiApi.adaptations).mockResolvedValue({items:[],page:0,size:20,total:0});
 vi.mocked(metaUiApi.rules).mockResolvedValue({source:'runtime',rulesetVersion:7,rulesetHash:'real-hash',rules:[{ruleId:'R1',name:'Regla real',version:7,priority:99,eventType:'ATTEMPT_EVALUATED',conditionSummary:'consecutiveFailures >= 3',enabled:true,actions:[{type:'REPEAT_ACTIVITY',hintLevel:null,feedbackStyle:null,targetConceptId:null}]}]});
 vi.mocked(metaUiApi.parameters).mockResolvedValue({source:'runtime',parametersVersion:9,parametersHash:'param-hash',values:{version:9,hintLevel:'GUIDED',difficultyAdjustmentEnabled:false,routeAdaptationEnabled:true,feedbackDetail:'CONCISE',failureThreshold:8,successThreshold:7,masteryThreshold:.73,maxHintsPerActivity:4,maxAttemptsBeforeReinforcement:6}});
});
afterEach(()=>{cleanup();vi.clearAllMocks();});
it('shows parsed runtime rules and every active parameter without write controls',async()=>{
 render(<MetaUiPage/>);fireEvent.click(screen.getByRole('button',{name:'Reglas'}));
 expect(await screen.findByText('Regla real')).toBeVisible();expect(screen.getByText(/Prioridad 99/)).toBeVisible();expect(screen.getByText('consecutiveFailures >= 3')).toBeVisible();expect(screen.getByText('REPEAT_ACTIVITY')).toBeVisible();
 fireEvent.click(screen.getByRole('button',{name:'Parámetros'}));await screen.findByText('Parámetros activos');
 for(const field of ['version','hintLevel','difficultyAdjustmentEnabled','routeAdaptationEnabled','feedbackDetail','failureThreshold','successThreshold','masteryThreshold','maxHintsPerActivity','maxAttemptsBeforeReinforcement']) expect(screen.getByText(field)).toBeVisible();
 expect(screen.getByText('0.73')).toBeVisible();expect(screen.queryByRole('switch')).toBeNull();expect(screen.queryByRole('button',{name:/guardar/i})).toBeNull();
});
it('switching students clears old data and ignores late responses',async()=>{
 let resolveA:(value:Overview)=>void=()=>{};
 vi.mocked(metaUiApi.overview).mockImplementation(id=>id==='A'?new Promise(resolve=>{resolveA=resolve;}):Promise.resolve(overview('B')));
 render(<MetaUiPage/>);await screen.findByRole('option',{name:/Estudiante …A/});
 fireEvent.change(screen.getByLabelText('Estudiante pseudónimo'),{target:{value:'A'}});
 fireEvent.change(screen.getByLabelText('Estudiante pseudónimo'),{target:{value:'B'}});
 expect(await screen.findByRole('heading',{name:'B'})).toBeVisible();resolveA(overview('A'));
 expect(screen.queryByRole('heading',{name:'A'})).toBeNull();
});
it('empty students, attempts and adaptations are explicit',async()=>{
 render(<MetaUiPage/>);await screen.findByRole('option',{name:/Estudiante …A/});fireEvent.change(screen.getByLabelText('Estudiante pseudónimo'),{target:{value:'A'}});
 fireEvent.click(screen.getByRole('button',{name:'Intentos'}));expect(await screen.findByText('Sin intentos registrados.')).toBeVisible();
 fireEvent.click(screen.getByRole('button',{name:'Adaptaciones'}));expect(await screen.findByText('Sin adaptaciones registradas.')).toBeVisible();
 cleanup();vi.mocked(metaUiApi.students).mockResolvedValue({items:[],page:0,size:20,total:0});render(<MetaUiPage/>);expect(await screen.findByText('Sin estudiantes registrados.')).toBeVisible();
});
it('API errors are safe and loading is visible',async()=>{
 vi.mocked(metaUiApi.rules).mockRejectedValue(new Error('SECRET_STACKTRACE'));
 render(<MetaUiPage/>);expect(screen.getAllByRole('status').length).toBeGreaterThan(0);
 fireEvent.click(screen.getByRole('button',{name:'Reglas'}));expect(await screen.findByRole('alert')).toHaveTextContent('No se pudo cargar');expect(screen.queryByText(/SECRET_STACKTRACE/)).toBeNull();
});
it('timeline sorts sequence, expands safe payloads and exposes partial/inconsistent states',()=>{
 const t=trace();t.events=[2,1].map(sequence=>({id:String(sequence),attemptId:'a1',studentId:'A',sessionId:null,requestId:'r',sequence,type:'EXECUTION_COMPLETED',source:'EXECUTION',eventVersion:1,occurredAt:'now',payload:{success:true,status:'OK',steps:7,goalReached:true,errorCount:0,traceHash:'hash'},payloadHash:'hash'}));
 const {rerender}=render(<TimelineView trace={t}/>);expect(screen.getByText('COMPLETE')).toBeVisible();const items=screen.getAllByRole('listitem');expect(items[0]).toHaveTextContent('1. EXECUTION_COMPLETED');fireEvent.click(within(items[0]!).getByText('1. EXECUTION_COMPLETED'));expect(within(items[0]!).getByText('goalReached')).toBeVisible();
 rerender(<TimelineView trace={{...t,events:[],traceStatus:'PARTIAL',gaps:['LEGACY_NO_EVENTS']}}/>);expect(screen.getByText(/intento creado antes/)).toBeVisible();expect(screen.getByText('No solicitado')).toBeVisible();expect(screen.getByText('Sin adaptación registrada.')).toBeVisible();
 rerender(<TimelineView trace={{...t,traceStatus:'INCONSISTENT',gaps:['EVENT_SEQUENCE_OR_OWNER']}}/>);expect(screen.getByRole('alert')).toHaveTextContent('evidencia inconsistente');
});
it('feedback providers render safe text without html execution',()=>{
 const t=trace();t.feedback=['OPENAI','GEMINI','FAKE','DISABLED'].map((provider,i)=>({feedbackId:String(i),attemptId:'a1',adaptationDecisionId:null,purpose:'FEEDBACK_GENERATION',message:'<img src=x onerror=bad()>',question:null,focus:null,hintStage:'CONCEPTUAL_HINT',language:'es',source:provider==='DISABLED'?'FALLBACK':provider as 'OPENAI'|'GEMINI'|'FAKE',provider:provider as 'OPENAI'|'GEMINI'|'FAKE'|'DISABLED',model:null,llmUsed:provider==='OPENAI'||provider==='GEMINI',fallbackReason:provider==='DISABLED'?'DISABLED':null,promptTemplateVersion:1,policyVersion:1,promptHash:'hash',sanitizedContextHash:'hash',createdAt:'now'}));
 const {container}=render(<TimelineView trace={t}/>);expect(container.querySelector('img')).toBeNull();expect(screen.getByRole('heading',{name:'GEMINI / GEMINI'})).toBeVisible();expect(screen.getByRole('heading',{name:/FAKE.*Proveedor de prueba/})).toBeVisible();expect(screen.getByRole('heading',{name:'DISABLED / FALLBACK'})).toBeVisible();
});
