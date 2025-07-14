import { Component } from '@angular/core';
import { PessoaType } from '../../models/pessoaType';
import { UserService } from '../../services/user.service';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { MatListModule } from '@angular/material/list';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatSidenavModule } from '@angular/material/sidenav';
import { RouterModule } from '@angular/router';
import { Router } from '@angular/router';
import { HorarioAtendimentoType } from '../../models/horarioAtendimento';
import { HttpClient } from '@angular/common/http';
import { MatChipsModule } from '@angular/material/chips';

@Component({
  selector: 'app-dashboard',
  imports: [MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    CommonModule,
    MatListModule,
    MatCardModule,
    MatDividerModule,
    MatSidenavModule,
    RouterModule,
    MatChipsModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  apiUrl = 'http://localhost:8080/';
  user: PessoaType | null = null;
  horarioAtendimentos: HorarioAtendimentoType[] = []

  constructor(private userService: UserService, private http: HttpClient) {
    this.user = this.userService.getLoggedInUser();
    this.buscarHorarioAtendimentoDiariosPorMedico()
  }

  buscarHorarioAtendimentoDiariosPorMedico() {
    var cpf = this.user?.cpf;
    this.http.get<HorarioAtendimentoType[]>(`${this.apiUrl}horario-atendimento/atendimentosDiarios/${cpf}`).subscribe({
      next: (response) => {
        this.horarioAtendimentos = response;
      }
    });
  }


}
