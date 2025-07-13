import { AlergiaType } from "./alergiaType";
import { FormacaoType } from "./formacaoType";

export interface UsuarioType {
  cpf: string;
  nome: string;
  sobrenome: string;
  email: string;
  endereco: string;
  dataNascimento: Date | null; 
  senha: string;
  tipo: string; 

  telefoneDDD?: string;
  telefoneNumero?: string;

  // Campos específicos para Médico
  numCrm?: string;
  ufCrm?: string;
  formacoes?: FormacaoType[];
  especialidades?: [];

  // Campos específicos para Paciente
  tipoSanguineo?: string;
  rhSanguineo?: string;
  peso?: number;
  altura?: number;
  historicoFamiliaDoencas?: string;
  alergias?: AlergiaType[];

}