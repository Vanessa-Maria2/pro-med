import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoginComponent } from "./componentes/login/login.component";
import { CadastroUsuarioComponent } from './componentes/cadastro-usuario/cadastro-usuario.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, LoginComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = './assets/home.png';
}
