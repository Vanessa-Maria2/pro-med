import { EspecialidadeType } from "./especialidadeType";
import { FormacaoType } from "./formacaoType";
import { PessoaType } from "./pessoaType";
import { UsuarioType } from "./UsuarioType";

export interface MedicoType {
    numCrm?: string;
    ufCrm?: string;
    formacoes?: FormacaoType[];
    especialidades?: EspecialidadeType[];
    pessoaRequestDto?: PessoaType,
    pessoaResponseDto?: PessoaType
}