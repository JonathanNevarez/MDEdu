import {Brand} from '../shared/Brand';
import {StudentAccess,StudentLoginPage} from '../features/student/StudentAccess';
import { Navigate, Outlet, Route, Routes, useParams } from 'react-router-dom';
import { NotFoundPage } from '../pages/NotFoundPage';
import { lazy, Suspense } from 'react';
import { TeacherAccess, TeacherLoginPage } from '../features/teacher/TeacherAccess';
import { HeaderLandscape, LoadingState } from '../shared/Visuals';
const LaboratoryPage = lazy(() => import('../features/programming/pages/LaboratoryPage').then(m => ({ default: m.LaboratoryPage })));
const AdventurePage = lazy(() => import('../features/game/AdventurePage').then(m => ({ default: m.AdventurePage })));
const MetaUiPage = lazy(() => import('../features/metaui/MetaUiPage').then(m => ({ default: m.MetaUiPage })));
function PublicLayout(){return <><header className="site-header public-header"><HeaderLandscape/><Brand/></header><main className="page-content" id="contenido"><Outlet/></main></>;}
function LegacyChallenge(){const {levelId}=useParams();return <Navigate to={`/aprender/${encodeURIComponent(levelId ?? '')}`} replace/>;}
export function App() {
 return <Routes>
  <Route element={<PublicLayout/>}>
   <Route path="ingresar" element={<StudentLoginPage/>}/>
   <Route path="docente/login" element={<TeacherLoginPage/>}/>
   <Route path="docente" element={<Suspense fallback={<LoadingState text="Preparando la vista docente…"/>}><TeacherAccess><MetaUiPage/></TeacherAccess></Suspense>}/>
   <Route path="*" element={<NotFoundPage/>}/>
  </Route>
  <Route element={<StudentAccess><Outlet/></StudentAccess>}>
   <Route path="aprender" element={<Suspense fallback={<LoadingState/>}><AdventurePage/></Suspense>}/>
   <Route path="aprender/:levelId" element={<Suspense fallback={<LoadingState/>}><AdventurePage/></Suspense>}/>
   <Route path="progreso" element={<Suspense fallback={<LoadingState/>}><AdventurePage progressOnly/></Suspense>}/>
   <Route path="laboratorio" element={<Suspense fallback={<LoadingState/>}><LaboratoryPage/></Suspense>}/>
  </Route>
  <Route index element={<Navigate to="/aprender" replace/>}/>
  <Route path="aventura" element={<Navigate to="/aprender" replace/>}/>
  <Route path="aventura/:levelId" element={<LegacyChallenge/>}/>
 </Routes>;
}
