import { Component } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';
import { LoginType } from '../../models/loginType';
import { HttpClient } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { PessoaType } from '../../models/pessoaType';

@Component({
  selector: 'app-login',
  imports: [FormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatDividerModule, MatIconModule, HttpClientModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {

  loginData: LoginType = { email: '', password: ''};
  apiUrl = 'http://localhost:8080/pessoa/login';

  constructor(private http: HttpClient,
              private router: Router,
              private userService: UserService) {}

  login() {
    this.http.post<PessoaType>(this.apiUrl, this.loginData).subscribe({
          next: (response) => {
            const user: PessoaType = {
              cpf: response.cpf,
              nome: response.nome,
              sobrenome: response.sobrenome,
              email: response.email,
              endereco: response.endereco,
              dataNascimento: response.dataNascimento ? new Date(response.dataNascimento) : null,
              senha: response.senha,
              tipo: response.tipo,
              telefones: response.telefones
            };
            this.userService.setLoggedInUser(user);
            this.router.navigate(['/home']);
          },
          error: (error) => {
            console.error('Erro no login:', error);
          },
        });  
    }

}
