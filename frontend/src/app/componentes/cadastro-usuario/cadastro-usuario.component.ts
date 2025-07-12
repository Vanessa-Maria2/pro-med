import { Component } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { NgxMaskDirective } from 'ngx-mask';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core'; 

export interface CadastroUsuarioType {
  cpf: string;
  nome: string;
  sobrenome: string;
  email: string;
  endereco: string;
  dataNascimento: Date | null; 
  senha: string;
  tipo: string; 

  // Campos para Telefone 
  telefoneDDD?: string;
  telefoneNumero?: string;

  // Campos específicos para Médico
  crm?: string;
  dataContratacao?: Date | null;
  

  // Campos específicos para Paciente
  tipoSanguineo?: string;
  peso?: number;
  altura?: number;

}

@Component({
  selector: 'app-cadastro-usuario',
  standalone: true, 
  imports: [FormsModule, 
    MatFormFieldModule, 
    MatInputModule, 
    MatButtonModule, 
    MatDividerModule, 
    MatIconModule, 
    CommonModule, 
    NgxMaskDirective,  
    MatSelectModule,       
    MatDatepickerModule,  
    MatNativeDateModule, ],

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
    tipo: '', 
    telefoneDDD: '',
    telefoneNumero: '',
    crm: '',
    dataContratacao: null,
    tipoSanguineo: '',
    peso: undefined,
    altura: undefined,
  };

  hidePassword = true
  userTypes: string[] = ['médico', 'gerente', 'recepcionista', 'paciente'];

  constructor() {}

  cadastrarUsuario() {
    console.log('Dados do cadastro:', this.cadastroData);
  }
}