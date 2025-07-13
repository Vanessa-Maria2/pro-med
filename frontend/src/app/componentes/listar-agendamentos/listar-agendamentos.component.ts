import { Component } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { MatListModule } from '@angular/material/list'; 
import { MatCardModule } from '@angular/material/card'; 
import { MatDividerModule } from '@angular/material/divider'; 
import { UserService } from '../../services/user.service'; 
import { PessoaType } from '../../models/pessoaType';
import { MatSidenavModule } from '@angular/material/sidenav';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { Router } from '@angular/router';
import { AgendamentoType } from '../../models/agendamentoType';

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
    RouterModule],
  templateUrl: './listar-agendamentos.component.html',
  styleUrl: './listar-agendamentos.component.css'
})
export class ListarAgendamentosComponent {

   agendamentos: AgendamentoType[] = [
    { especialidade: 'Cardiologia', medico: 'Dra. Ana Costa', data: '2025-07-25', horario: '10:00' },
    { especialidade: 'Dermatologia', medico: 'Dr. Bruno Lima', data: '2025-08-01', horario: '14:30' },
    { especialidade: 'Odontologia', medico: 'Dra. Carla Souza', data: '2025-08-10', horario: '09:15' },
  ];

  constructor(private userService: UserService, private router: Router) {} 

  // Método placeholder para a função de agendar consulta
  agendarConsulta(): void {
    console.log('Navegando para a página de agendamento de consulta...');
    this.router.navigate(['/agendar-consulta']); 
  }


}
