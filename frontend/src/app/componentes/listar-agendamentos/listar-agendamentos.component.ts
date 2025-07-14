import { Component } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { MatListModule } from '@angular/material/list';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { UserService } from '../../services/user.service';
import { MatSidenavModule } from '@angular/material/sidenav';
import { RouterModule } from '@angular/router';
import { Router } from '@angular/router';
import { HorarioAtendimentoType } from '../../models/horarioAtendimento';
import { HttpClient } from '@angular/common/http';
import { MatChipsModule } from '@angular/material/chips';
import { PessoaType } from '../../models/pessoaType';

@Component({
  selector: 'app-listar-agendamentos',
  imports: [MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    CommonModule,
    MatListModule,
    MatCardModule,
    MatDividerModule,
    MatSidenavModule,
    RouterModule,
    MatChipsModule
  ],
  templateUrl: './listar-agendamentos.component.html',
  styleUrl: './listar-agendamentos.component.css'
})
export class ListarAgendamentosComponent {
  apiUrl = 'http://localhost:8080/';
  dataAtual: Date = new Date();
  dataFormatada: string = '';
  user: PessoaType | null = null;


  horarioAtendimentos: HorarioAtendimentoType[] = []

  constructor(private userService: UserService, private router: Router, private http: HttpClient) {
    this.user = this.userService.getLoggedInUser();
   }

  agendarConsulta(): void {
    this.router.navigate(['/agendar-consulta']);
  }

  buscarHorarioAtendimento() {
    this.http.get<HorarioAtendimentoType[]>(`${this.apiUrl}horario-atendimento`).subscribe({
      next: (response) => {
        this.horarioAtendimentos = response;
      },
      error: (error) => {
      },
    });
  }

  buscarHorarioAtendimentoMedico(cpf: string | undefined) {
   this.http.get<HorarioAtendimentoType[]>(`${this.apiUrl}horario-atendimento/buscarPorMedico/${cpf}`).subscribe({
      next: (response) => {
        this.horarioAtendimentos = response;
      }
    });
  }

  buscarHorarioAtendimentoPaciente(cpf: string | undefined) {
   this.http.get<HorarioAtendimentoType[]>(`${this.apiUrl}horario-atendimento/buscarPorPaciente/${cpf}`).subscribe({
      next: (response) => {
        this.horarioAtendimentos = response;
      }
    });
  }
  
  ngOnInit(): void {
    var tipo = this.userService.getLoggedInUser()?.tipo;

    if(tipo == "médico") {
      this.buscarHorarioAtendimentoMedico(this.userService.getLoggedInUser()?.cpf)
    } else if(tipo == "paciente") {
      this.buscarHorarioAtendimentoPaciente(this.userService.getLoggedInUser()?.cpf)
    } else{
      this.buscarHorarioAtendimento()
    }
  }

  criarHorarioAtendimento() {
    
  }

}
