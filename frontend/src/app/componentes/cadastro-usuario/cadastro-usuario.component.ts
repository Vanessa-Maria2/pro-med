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
import { HttpClient } from '@angular/common/http';
import { EspecialidadeType } from '../../models/especialidadeType';
import { AlergiaType } from '../../models/alergiaType';
import { TelefoneType } from '../../models/telefoneType';
import { PessoaType } from '../../models/pessoaType';
import { MedicoType } from '../../models/medicoType';
import { PacienteType } from '../../models/pacienteType';


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

  especialidadesList: EspecialidadeType[] = [];

  especialidades = new FormControl<EspecialidadeType[]>([]);

  hidePassword = true
  userTypes: string[] = ['médico', 'gerente', 'recepcionista', 'paciente'];

  formacoes: FormacaoType[] = [];
  alergias: AlergiaType[] = [];
  telefones: TelefoneType[] = [];

  apiUrl = 'http://localhost:8080/';

  constructor(private http: HttpClient) {
    this.especialidades.valueChanges.subscribe((lista) => {
      this.cadastroData.medico.especialidades = lista || [];
    });
  }

  cadastrarUsuario() {

    if (this.cadastroData.pessoa.tipo === 'médico') {
      const medico: MedicoType = {
        numCrm: this.cadastroData.medico.numCrm,
        ufCrm: this.cadastroData.medico.ufCrm,
        formacoes: this.cadastroData.medico.formacoes,
        especialidades: this.cadastroData.medico.especialidades,
        pessoaRequestDto: this.cadastroData.pessoa
      };

      this.http.post(`${this.apiUrl}medico`, medico).subscribe({
        next: (response) => console.log('Médico cadastrado com sucesso:', response),
        error: (error) => console.error('Erro ao cadastrar médico:', error),
      });

    } else if (this.cadastroData.pessoa.tipo === 'paciente') {
      const paciente: PacienteType = {
        tipoSanguineo: this.cadastroData.paciente.tipoSanguineo,
        rhSanguineo: this.cadastroData.paciente.rhSanguineo,
        peso: this.cadastroData.paciente.peso,
        altura: this.cadastroData.paciente.altura,
        historicoFamiliaDoencas: this.cadastroData.paciente.historicoFamiliaDoencas,
        alergias: this.alergias,
        pessoaRequestDto: this.cadastroData.pessoa
      };

      this.http.post(`${this.apiUrl}paciente`, paciente).subscribe({
        next: (response) => console.log('Paciente cadastrado com sucesso:', response),
        error: (error) => console.error('Erro ao cadastrar paciente:', error),
      });

    } else {
      this.http.post(`${this.apiUrl}pessoa`, this.cadastroData.pessoa).subscribe({
        next: (response) => console.log('Pessoa cadastrada com sucesso:', response),
        error: (error) => console.error('Erro ao cadastrar pessoa:', error),
      });
    }
  }

  buscarEspecialidades() {
    this.http.get<EspecialidadeType[]>(`${this.apiUrl}especialidade`).subscribe({
      next: (response) => {
        this.especialidadesList = response;
      },
      error: (error) => {
        console.error('Erro ao buscar especialidades:', error);
      }
    });
  }

  onTipoUsuarioChange(tipo: string) {
    this.cadastroData.pessoa.tipo = tipo;
    if (tipo === 'médico') {
      this.buscarEspecialidades();
    } else {
      this.especialidadesList = [];
    }
  }


  get itensFormacoes(): FormacaoType[] {
    return this.formacoes;
  }

  set itensFormacoes(value: FormacaoType[]) {
    this.formacoes = value;
    this.cadastroData.medico.formacoes = value;
  }

  get itensAlergias(): AlergiaType[] {
    return this.alergias;
  }

  set itensAlergias(value: AlergiaType[]) {
    this.alergias = value;
    this.cadastroData.paciente.alergias = value;
  }


  get itensTelefones(): TelefoneType[] {
    return this.telefones;
  }

  set itensTelefones(value: TelefoneType[]) {
    this.telefones = value;
    this.cadastroData.pessoa.telefones = value;
  }

}