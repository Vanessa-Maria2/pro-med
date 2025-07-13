// frontend/src/app/app.routes.ts
import { Routes } from '@angular/router';
import { LoginComponent } from './componentes/login/login.component';
import { CadastroUsuarioComponent } from './pages/cadastro-usuario/cadastro-usuario.component'; // Garanta o caminho correto (se está em 'pages')
import { HomeComponent } from './pages/home/home.component';
import { AgendarConsultaComponent } from './pages/agendar-consulta/agendar-consulta.component'; // Garanta o caminho correto (se está em 'pages')

export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'cadastro', component: CadastroUsuarioComponent },
    { path: 'home', component: HomeComponent },
    { path: 'agendar-consulta', component: AgendarConsultaComponent },
    { path: '', redirectTo: '/login', pathMatch: 'full' } // Redireciona para /login como padrão. Mude para /home se preferir para teste.
];
