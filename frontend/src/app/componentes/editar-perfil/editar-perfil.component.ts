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
import { UsuarioType } from '../../models/UsuarioType';;
import { TelefoneType } from '../../models/telefoneType';
import { CampoComponent } from '../campo/campo.component';

@Component({
  selector: 'app-editar-perfil',
  standalone: true,
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
    ReactiveFormsModule, CampoComponent],
  templateUrl: './editar-perfil.component.html',
  styleUrl: './editar-perfil.component.css'
})
export class EditarPerfilComponent {

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
  
    telefones: TelefoneType[] = [];
    
    cadastrarUsuario() {

    }

    get itensTelefones(): TelefoneType[] {
    return this.telefones;
  }

  set itensTelefones(value: TelefoneType[]) {
    this.telefones = value;
    this.cadastroData.pessoa.telefones = value;
  }
}
