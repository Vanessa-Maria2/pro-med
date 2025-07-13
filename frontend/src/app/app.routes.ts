import { Routes } from '@angular/router';
import { LoginComponent } from './componentes/login/login.component';
import { CadastroUsuarioComponent } from './componentes/cadastro-usuario/cadastro-usuario.component';
import { HomeComponent } from './componentes/home/home.component';
import { DashboardComponent } from './componentes/dashboard/dashboard.component';
import { AgendarConsultaComponent } from './componentes/agendar-consulta/agendar-consulta.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'cadastro', component: CadastroUsuarioComponent },
  { 
    path: '', 
    component: HomeComponent, 
    children: [
      { path: 'home', component: DashboardComponent },
      { path: 'dashboard', component: DashboardComponent },
      { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
      { path: 'agendar-consulta', component: AgendarConsultaComponent },
    ]
  },
  { path: '**', redirectTo: 'login' }
];
