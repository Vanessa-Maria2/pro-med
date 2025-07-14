import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { Router } from '@angular/router';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { MedicoType } from '../../models/medicoType';
import { HorarioAtendimentoType } from '../../models/horarioAtendimento';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from '../../services/user.service';
import { PessoaType } from '../../models/pessoaType';

@Component({
  selector: 'app-agendar-consulta',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatDatepickerModule,
    MatNativeDateModule,
    HttpClientModule
  ],
  templateUrl: './agendar-consulta.component.html',
  styleUrl: './agendar-consulta.component.css'
})
export class AgendarConsultaComponent implements OnInit {

  apiUrl = 'http://localhost:8080/';

  selectedDoctorId: number | null = null;
  doctors: MedicoType[] = [];
  horariosAtendimentos: HorarioAtendimentoType[] = [];
  selectHorarioAtendimentoId: number | null = null;

  constructor(private router: Router, private http: HttpClient, private snackBar: MatSnackBar, private userService: UserService) {
    this.buscarMedicos();
  }

  ngOnInit(): void {
    this.buscarMedicos();
  }

  onDoctorChange(): void {
    if (this.selectedDoctorId) {
      this.buscarHorarioAtendimento();
    }
  }

  agendar(): void {

    if (this.selectedDoctorId && this.selectHorarioAtendimentoId) {
      const agenda = {
        horarioAtendimentoId: this.selectHorarioAtendimentoId,
        cpf: this.userService.getLoggedInUser()?.cpf
      }

      this.http.post(`${this.apiUrl}horario-atendimento/agendar`, agenda, { responseType: 'text' }).subscribe({
        next: (response) => {
          this.mensagem('Horário de atendimento agendado com sucesso!', 'sucesso');
        },
        error: (error) => {
          this.mensagem('Erro ao agendar horário de atendimento!', 'erro');
        },
      });
    }
  }

  private resetForm(): void {
    this.selectedDoctorId = null;
    this.selectHorarioAtendimentoId = null;
    this.doctors = [];
  }

  cancel(): void {
    this.router.navigate(['/home']);
  }

  buscarMedicos() {
    this.http.get<MedicoType[]>(`${this.apiUrl}medico/buscarTodos`).subscribe({
      next: (response) => {
        this.doctors = response;
      }
    });
  }

  buscarHorarioAtendimento() {
    this.http.get<HorarioAtendimentoType[]>(`${this.apiUrl}horario-atendimento/buscarPorMedicoStatus/${this.selectedDoctorId}?status=DISPONIVEL`).subscribe({
      next: (response) => {
        this.horariosAtendimentos = response;
      }
    });
  }

  mensagem(mensagem: string, tipo: 'sucesso' | 'erro') {
    this.snackBar.open(mensagem, 'Fechar', {
      duration: 4000,
      verticalPosition: 'top',
      horizontalPosition: 'right',
      panelClass: tipo === 'sucesso' ? 'snackbar-sucesso' : 'snackbar-erro',
    });
  }
}