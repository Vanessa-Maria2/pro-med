import { AlergiaType } from "./alergiaType";
import { EspecialidadeType } from "./especialidadeType";
import { FormacaoType } from "./formacaoType";
import { MedicoType } from "./medicoType";
import { PacienteType } from "./pacienteType";
import { PessoaType } from "./pessoaType";
import { TelefoneType } from "./telefoneType";

export interface UsuarioType {
  pessoa: PessoaType;
  medico: MedicoType;
  paciente: PacienteType;
}