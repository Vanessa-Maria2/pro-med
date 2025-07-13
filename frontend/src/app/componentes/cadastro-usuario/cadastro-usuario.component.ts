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
import { FormacaoType } from '../../models/formacaoType';
import { HttpClient } from '@angular/common/http';
import { EspecialidadeType } from '../../models/especialidadeType';
import { AlergiaType } from '../../models/alergiaType';
import { TelefoneType } from '../../models/telefoneType';
import { PessoaType } from '../../models/pessoaType';
import { MedicoType } from '../../models/medicoType';
import { PacienteType } from '../../models/pacienteType';
import { DoencaType } from '../../models/doencaType';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { CampoComponent } from '../campo/campo.component';


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
    ReactiveFormsModule, CampoComponent],
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

  especialidadesList: EspecialidadeType[] = [];

  especialidades = new FormControl<EspecialidadeType[]>([]);


  alergiasList: AlergiaType[] = [];

  alergias = new FormControl<AlergiaType[]>([]);

  doencasList: DoencaType[] = [];

  doencas = new FormControl<DoencaType[]>([]);

  hidePassword = true
  userTypes: string[] = ['médico', 'gerente', 'recepcionista', 'paciente'];

  formacoes: FormacaoType[] = [];
  telefones: TelefoneType[] = [];

  apiUrl = 'http://localhost:8080/';

  constructor(private http: HttpClient, private snackBar: MatSnackBar, private router: Router) {
    this.especialidades.valueChanges.subscribe((lista) => {
      this.cadastroData.medico.especialidades = lista || [];
    });

     this.alergias.valueChanges.subscribe((lista) => {
      this.cadastroData.paciente.alergias = lista || [];
    });

      this.doencas.valueChanges.subscribe((lista) => {
      this.cadastroData.paciente.doencas = lista || [];
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
        next: (response) => {
          this.mensagem('Médico cadastrado com sucesso!', 'sucesso');
          this.router.navigate(['/login']);
        },
        error: (error) => {
          this.mensagem('Erro ao cadastrar médico!', 'erro');
        },
      });

    } else if (this.cadastroData.pessoa.tipo === 'paciente') {
      const paciente: PacienteType = {
        tipoSanguineo: this.cadastroData.paciente.tipoSanguineo,
        rhSanguineo: this.cadastroData.paciente.rhSanguineo,
        peso: this.cadastroData.paciente.peso,
        altura: this.cadastroData.paciente.altura,
        historicoFamiliaDoencas: this.cadastroData.paciente.historicoFamiliaDoencas,
        alergias: this.cadastroData.paciente.alergias,
        doencas: this.cadastroData.paciente.doencas,
        pessoaRequestDto: this.cadastroData.pessoa
      };

      this.http.post(`${this.apiUrl}paciente`, paciente).subscribe({
        next: (response) => {
          this.mensagem('Paciente cadastrado com sucesso!', 'sucesso');
          this.router.navigate(['/login']);
        },
        error: (error) => {
          this.mensagem('Erro ao cadastrar paciente!', 'erro');
        },
      });

    } else {
      this.http.post(`${this.apiUrl}pessoa`, this.cadastroData.pessoa).subscribe({
         next: (response) => {
          this.mensagem('Usuário cadastrado com sucesso!', 'sucesso');
          this.router.navigate(['/login']);
        },
        error: (error) => {
          this.mensagem('Erro ao cadastrar usuário!', 'erro');
        }
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

  buscarAlergias() {
    this.http.get<AlergiaType[]>(`${this.apiUrl}alergia`).subscribe({
      next: (response) => {
        this.alergiasList = response;
      },
      error: (error) => {
        console.error('Erro ao buscar alergias:', error);
      }
    });
  }

   buscarDoencas() {
    this.http.get<DoencaType[]>(`${this.apiUrl}doenca`).subscribe({
      next: (response) => {
        this.doencasList = response;
      },
      error: (error) => {
        console.error('Erro ao buscar doencas:', error);
      }
    });
  }

  onTipoUsuarioChange(tipo: string) {
    this.cadastroData.pessoa.tipo = tipo;
    if (tipo === 'médico') {
      this.buscarEspecialidades();
    } else if (tipo === "paciente") {
      this.buscarAlergias();
      this.buscarDoencas();
    } else {
      this.especialidadesList = [];
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

  get itensFormacoes(): FormacaoType[] {
    return this.formacoes;
  }

  set itensFormacoes(value: FormacaoType[]) {
    this.formacoes = value;
    this.cadastroData.medico.formacoes = value;
  }

  get itensTelefones(): TelefoneType[] {
    return this.telefones;
  }

  set itensTelefones(value: TelefoneType[]) {
    this.telefones = value;
    this.cadastroData.pessoa.telefones = value;
  }

}