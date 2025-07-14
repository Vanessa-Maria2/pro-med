import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { UserService } from '../../services/user.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-configurar-perfil',
  imports: [MatIconModule, MatButtonModule, RouterModule],
  templateUrl: './configurar-perfil.component.html',
  styleUrl: './configurar-perfil.component.css'
})
export class ConfigurarPerfilComponent {

  constructor(private userService: UserService, private router: Router, private http: HttpClient) {
  }

  sair() {
   this.userService.logout();
   this.router.navigate(['/login']);
  }
}
