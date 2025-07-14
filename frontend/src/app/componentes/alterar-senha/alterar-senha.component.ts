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
   cadastroData: UsuarioType = {
      pessoa: {
        cpf: '',
        nome: '',
        sobrenome: '',
        email: '',
        endereco: '',
        dataNascimento: null,
        senha: '',
        telefones: [],
        tipo: '',
      },
      paciente: {
        alergias: [],
        doencas: [],
        historicoFamiliaDoencas: '',
        tipoSanguineo: '',
        peso: undefined,
        altura: undefined
      },
      medico: {
        numCrm: '',
        ufCrm: '',
        especialidades: [],
        formacoes: []
      }
    };


  hidePassword = true

  confirmarSenha: string = '';


   alterarSenha() {
    if (this.cadastroData.pessoa.senha !== this.confirmarSenha) {
      alert('As senhas não coincidem.');
      return;
    }

    // Aqui vai a lógica para alterar a senha (ex: chamada para backend)
    console.log('Senha alterada com sucesso:', this.cadastroData.pessoa.senha);

    // Resetar os campos de senha após sucesso (opcional)
    this.cadastroData.pessoa.senha = '';
    this.confirmarSenha = '';
  }
}
