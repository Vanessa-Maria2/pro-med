import { Component } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { UsuarioType } from '../../models/UsuarioType';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-alterar-senha',
  imports: [FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDividerModule,
    MatIconModule,
    CommonModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    ReactiveFormsModule],
  templateUrl: './alterar-senha.component.html',
  styleUrl: './alterar-senha.component.css'
})
export class AlterarSenhaComponent {
  hidePasswordSenhaAtual = true;
  hidePasswordSenha = true;
  hidePasswordConfirmarSenha = true;

  confirmarSenha: string = '';
  senhaAtual: string = '';
  senha: string = '';
  apiUrl = 'http://localhost:8080/';

  constructor(private http: HttpClient, private snackBar: MatSnackBar, private userService: UserService) {

  }

  alterarSenha() {
     console.log("Senha atual:", this.senhaAtual);
  console.log("Nova senha:", this.senha);
  console.log("Confirmação:", this.confirmarSenha);
    if (this.senha === this.confirmarSenha) {
      
      const email = this.userService.getLoggedInUser()?.email
      const senhas = {
        "novaSenha": this.senha,
        "senhaAtual": this.senhaAtual,
        "confirmarSenha": this.confirmarSenha
      }
      this.http.put(`${this.apiUrl}pessoa/${email}/senha`, senhas).subscribe({
        next: (response) => {
          this.mensagem('Senha alterada com sucesso!', 'sucesso')
        },
        error: (error) => {
          this.mensagem('Erro ao tentar alterar senhas!', 'erro');
        },
      });
    } else {
      this.mensagem('A nova senha e confirma senha são diferentes', 'erro');
    }
  }
  

  mensagem(mensagem: string, tipo: 'sucesso' | 'erro') {
    this.snackBar.open(mensagem, 'Fechar', {
      duration: 4000,
      verticalPosition: 'top',
      horizontalPosition: 'right',
      panelClass: tipo === 'sucesso' ? 'snackbar-sucesso' : 'snackbar-erro',
    });
  }

}
