import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { UserService } from '../../services/user.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { EditarPerfilComponent } from "../editar-perfil/editar-perfil.component";
import { AlterarSenhaComponent } from "../alterar-senha/alterar-senha.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-configurar-perfil',
  imports: [MatIconModule, MatButtonModule, RouterModule, EditarPerfilComponent, AlterarSenhaComponent, CommonModule],
  templateUrl: './configurar-perfil.component.html',
  styleUrl: './configurar-perfil.component.css'
})
export class ConfigurarPerfilComponent {
  secaoAtiva: 'perfil' | 'senha' | null = 'perfil';


  constructor(private userService: UserService, private router: Router, private http: HttpClient) {
  }

  mostrarEditarPerfil() {
    this.secaoAtiva = 'perfil';
  }

  mostrarAlterarSenha() {
    this.secaoAtiva = 'senha';
  }

  sair() {
   this.userService.logout();
   this.router.navigate(['/login']);
  }
}
