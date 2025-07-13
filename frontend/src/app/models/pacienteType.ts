import { AlergiaType } from "./alergiaType";
import { PessoaType } from "./pessoaType";
import { UsuarioType } from "./UsuarioType";

export interface PacienteType {
    tipoSanguineo?: string;
    rhSanguineo?: string;
    peso?: number;
    altura?: number;
    historicoFamiliaDoencas?: string;
    alergias?: AlergiaType[];
    pessoaRequestDto?: PessoaType;
}