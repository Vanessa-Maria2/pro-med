// import { Routes } from '@angular/router';

// export const routes: Routes = [];
import { Routes } from '@angular/router';
import { CadastroUsuarioComponent } from './pages/cadastro-usuario/cadastro-usuario.component'; // Importe o novo componente
import { LoginComponent } from './componentes/login/login.component'; // Já existe, apenas para referência

export const routes: Routes = [
    { path: 'login', component: LoginComponent }, // Rota existente para o login
    { path: 'cadastro', component: CadastroUsuarioComponent }, // Nova rota para o cadastro
    { path: '', redirectTo: '/login', pathMatch: 'full' } // Redireciona para login como padrão
];