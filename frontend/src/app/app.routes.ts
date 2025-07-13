// import { Routes } from '@angular/router';

// export const routes: Routes = [];
import { Routes } from '@angular/router';
import { LoginComponent } from './componentes/login/login.component';
import { CadastroUsuarioComponent } from './componentes/cadastro-usuario/cadastro-usuario.component';
import { HomeComponent } from './componentes/home/home.component';

export const routes: Routes = [
    { path: 'login', component: LoginComponent }, // Rota existente para o login
    { path: 'cadastro', component: CadastroUsuarioComponent }, // Nova rota para o cadastro
    { path: '', redirectTo: '/login', pathMatch: 'full' }, // Redireciona para login como padrão
    { path: 'home', component: HomeComponent}
];
