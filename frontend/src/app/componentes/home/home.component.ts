import { Component, OnInit } from '@angular/core'; 
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { MatListModule } from '@angular/material/list'; 
import { MatCardModule } from '@angular/material/card'; 
import { MatDividerModule } from '@angular/material/divider'; 
import { UserService, LoggedInUser } from '../../services/user.service'; 


// Interface para um agendamento (modelo mock)
interface Agendamento {
  especialidade: string;
  medico: string;
  data: string;
  horario: string;
}

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    CommonModule,
    MatListModule,      
    MatCardModule,      
    MatDividerModule    
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit { 
  loggedInUser: LoggedInUser | null = null; 

  // Dados mockados de agendamentos para o Paciente
  agendamentos: Agendamento[] = [
    { especialidade: 'Cardiologia', medico: 'Dra. Ana Costa', data: '2025-07-25', horario: '10:00' },
    { especialidade: 'Dermatologia', medico: 'Dr. Bruno Lima', data: '2025-08-01', horario: '14:30' },
    { especialidade: 'Odontologia', medico: 'Dra. Carla Souza', data: '2025-08-10', horario: '09:15' },
  ];

  constructor(private userService: UserService) {} 

  ngOnInit(): void {
    // Assina o Observable para obter as informações do usuário logado
    this.userService.currentUser.subscribe(user => {
      this.loggedInUser = user;
    });

  }

  // Método placeholder para a função de agendar consulta
  agendarConsulta(): void {
    console.log('Navegar para a página de agendamento de consulta...');
    // this.router.navigate(['/agendar-consulta']);
  }

  // Método para fazer logout 
  logout(): void {
    this.userService.logout();
    // this.router.navigate(['/login']); // Redireciona para o login após o logout
  }
}