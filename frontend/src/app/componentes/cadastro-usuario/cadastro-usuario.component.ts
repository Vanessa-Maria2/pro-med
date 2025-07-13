import { Component } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { NgxMaskDirective } from 'ngx-mask';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core'; 
import { UsuarioType } from '../../models/UsuarioType';
import { FormacaoComponent } from "../formacao/formacao.component";
import { FormacaoType } from '../../models/formacaoType';


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
    MatNativeDateModule,
    ReactiveFormsModule, FormacaoComponent],
  templateUrl: './cadastro-usuario.component.html',
  styleUrl: './cadastro-usuario.component.css',
})
export class CadastroUsuarioComponent {
  cadastroData: UsuarioType = {
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
    numCrm: '',
    ufCrm: '',
    tipoSanguineo: '',
    peso: undefined,
    altura: undefined,
    historicoFamiliaDoencas: '',
    formacoes: [],
    especialidades: [],
    alergias: []
  };


  toppingList: string[] = ['Queijo', 'Tomate', 'Cebola', 'Pimentão', 'Azeitona'];

  toppings = new FormControl<string[]>([]);

  hidePassword = true
  userTypes: string[] = ['médico', 'gerente', 'recepcionista', 'paciente'];

  constructor() {}

  cadastrarUsuario() {
    console.log('Dados do cadastro:', this.cadastroData);
  }

  formacoes: FormacaoType[] = [];
  alergias: FormacaoType[] = [];


  get itensFormacoes(): FormacaoType[] {
    return this.formacoes;
  }

  set itensFormacoes(value: FormacaoType[]) {
    this.formacoes = value;
    this.cadastroData.formacoes = value;
  }

  get itensAlergias(): FormacaoType[] {
    return this.alergias;
  }

  set itensAlergias(value: FormacaoType[]) {
    this.alergias = value;
    this.cadastroData.alergias = value;
  }

}