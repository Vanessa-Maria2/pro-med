import { TelefoneType } from "./telefoneType";

export interface PessoaType {
      cpf: string;
      nome: string;
      sobrenome: string;
      email: string;
      endereco: string;
      dataNascimento: Date | null; 
      senha: string;
      tipo: string; 
      telefones: TelefoneType[];
}