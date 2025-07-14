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

  horarioAtendimentos: HorarioAtendimentoType[] = []

  constructor(private userService: UserService, private router: Router, private http: HttpClient) {
   
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

  ngOnInit(): void {
    this.buscarHorarioAtendimento()
  }

}
