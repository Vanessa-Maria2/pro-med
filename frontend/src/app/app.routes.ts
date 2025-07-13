import { Routes } from '@angular/router';
import { LoginComponent } from './componentes/login/login.component';
import { HomeComponent } from './componentes/home/home.component'; 
import { AgendarConsultaComponent } from './componentes/agendar-consulta/agendar-consulta.component'; 

export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'home', component: HomeComponent },
    { path: 'agendar-consulta', component: AgendarConsultaComponent },  
    { path: '', redirectTo: '/login', pathMatch: 'full' }
];