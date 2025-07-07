import { Component } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common'; // Importe CommonModule para ngIf, ngFor

// Defina uma interface para os dados do formulário de cadastro,
// com base em PessoaRequestDto.java do backend
export interface CadastroUsuarioType {
  cpf: string;
  nome: string;
  sobrenome: string;
  email: string;
  endereco: string;
  dataNascimento: Date | null; // Usar Date ou string para a data
  senha: string;
  // Adicione outros campos conforme PessoaRequestDto se precisar
}

@Component({
  selector: 'app-cadastro-usuario',
  standalone: true, // Adicione se não estiver usando módulos
  imports: [FormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatDividerModule, MatIconModule, CommonModule],
  templateUrl: './cadastro-usuario.component.html',
  styleUrl: './cadastro-usuario.component.css',
})
export class CadastroUsuarioComponent {
  cadastroData: CadastroUsuarioType = {
    cpf: '',
    nome: '',
    sobrenome: '',
    email: '',
    endereco: '',
    dataNascimento: null,
    senha: '',
  };

  constructor() {}

  cadastrarUsuario() {
    // Lógica para enviar os dados para o backend (vamos ver depois)
    console.log('Dados do cadastro:', this.cadastroData);
  }
}