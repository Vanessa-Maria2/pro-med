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

interface Especialidade {
  id: number;
  nome: string;
}

interface Medico {
  id: number;
  nome: string;
  especialidadeId: number; 
}

interface Horario {
  id: number;
  hora: string;
  data: string;
  medicoId: number; 
  disponivel: boolean;
}


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

  selectedSpecialtyId: number | null = null;
  selectedDoctorId: number | null = null;
  selectedDate: Date | null = null;
  selectedTimeSlotId: number | null = null;

  specialties: Especialidade[] = [];
  doctors: Medico[] = [];
  availableDates: Date[] = [];
  availableTimeSlots: Horario[] = [];

  constructor(private router: Router, private http: HttpClient) {} 

  ngOnInit(): void {
    this.loadSpecialties();
  }

  private loadSpecialties(): void {
    // TODO: Fazer chamada HTTP para o backend para buscar especialidades
   

    // POR ENQUANTO (para continuar testando o front sem backend):
    // REMOVER APÓS INTEGRAR
    this.specialties = [
      { id: 1, nome: 'Cardiologia' },
      { id: 2, nome: 'Dermatologia' },
      { id: 3, nome: 'Pediatria' },
      { id: 4, nome: 'Clínico Geral' },
    ];
    console.log('Componente AgendarConsulta inicializado. Especialidades carregadas (mock ou API).');
  }

  onSpecialtyChange(): void {
    this.selectedDoctorId = null;
    this.selectedDate = null;
    this.selectedTimeSlotId = null;
    this.doctors = []; 
    this.availableDates = [];
    this.availableTimeSlots = [];

    if (this.selectedSpecialtyId) {
      // TODO: Fazer chamada HTTP para o backend para buscar médicos por especialidade
      
      // POR ENQUANTO (para continuar testando o front sem backend):
      // REMOVER APÓS INTEGRAR
      const mockAllDoctors = [
        { id: 101, nome: 'Dr(a). Ana Costa', especialidadeId: 1 },
        { id: 102, nome: 'Dr(a). Bruno Lima', especialidadeId: 2 },
        { id: 103, nome: 'Dr(a). Carla Souza', especialidadeId: 3 },
        { id: 104, nome: 'Dr(a). Daniel Alves', especialidadeId: 1 },
        { id: 105, nome: 'Dr(a). Eva Mendes', especialidadeId: 4 },
      ];
      this.doctors = mockAllDoctors.filter(doc => doc.especialidadeId === this.selectedSpecialtyId);
    }
    console.log('onSpecialtyChange - Especialidade selecionada. Médicos filtrados/carregados.');
  }

  onDoctorChange(): void {
    this.selectedDate = null;
    this.selectedTimeSlotId = null;
    this.availableDates = []; 
    this.availableTimeSlots = [];

    if (this.selectedDoctorId) {
      // TODO: Fazer chamada HTTP para o backend para buscar datas disponíveis para o médico
    
      // POR ENQUANTO (para continuar testando o front sem backend):
      // EMOVER APÓS INTEGRAR
      const mockAllTimeSlots = [
        { id: 1001, hora: '09:00', data: this.getFormattedDateForMock(0), medicoId: 101, disponivel: true },
        { id: 1003, hora: '11:00', data: this.getFormattedDateForMock(1), medicoId: 101, disponivel: true },
        { id: 2001, hora: '14:00', data: this.getFormattedDateForMock(0), medicoId: 102, disponivel: true },
        { id: 2002, hora: '15:00', data: this.getFormattedDateForMock(2), medicoId: 102, disponivel: true },
        { id: 3001, hora: '08:30', data: this.getFormattedDateForMock(0), medicoId: 103, disponivel: true },
      ];
      const datesForSelectedDoctor = mockAllTimeSlots
        .filter(slot => slot.medicoId === this.selectedDoctorId && slot.disponivel)
        .map(slot => slot.data);
      this.availableDates = [...new Set(datesForSelectedDoctor)]
        .map(dateStr => new Date(dateStr + 'T00:00:00'))
        .sort((a, b) => a.getTime() - b.getTime());

      console.log('onDoctorChange - Médico selecionado. Datas disponíveis carregadas.');
    }
  }

  onDateChange(): void {
    this.selectedTimeSlotId = null;
    this.availableTimeSlots = []; 

    if (this.selectedDoctorId && this.selectedDate) {
      const selectedDateFormatted = this.selectedDate.toISOString().split('T')[0];

      // TODO: Fazer chamada HTTP para o backend para buscar horários disponíveis para o médico e data
      
      // POR ENQUANTO (para continuar testando o front sem backend):
      // REMOVER APÓS INTEGRAR
      const mockAllTimeSlots = [
        { id: 1001, hora: '09:00', data: this.getFormattedDateForMock(0), medicoId: 101, disponivel: true },
        { id: 1002, hora: '10:00', data: this.getFormattedDateForMock(0), medicoId: 101, disponivel: true },
        { id: 1003, hora: '11:00', data: this.getFormattedDateForMock(1), medicoId: 101, disponivel: true },
        { id: 1004, hora: '14:00', data: this.getFormattedDateForMock(1), medicoId: 101, disponivel: false },
        { id: 2001, hora: '14:00', data: this.getFormattedDateForMock(0), medicoId: 102, disponivel: true },
        { id: 2002, hora: '15:00', data: this.getFormattedDateForMock(2), medicoId: 102, disponivel: true },
        { id: 3001, hora: '08:30', data: this.getFormattedDateForMock(0), medicoId: 103, disponivel: true },
      ];
      this.availableTimeSlots = mockAllTimeSlots.filter(
        slot =>
          slot.medicoId === this.selectedDoctorId &&
          slot.data === selectedDateFormatted &&
          slot.disponivel
      );
      console.log('onDateChange - Horários filtrados para a data e médico.');
    }
  }

  // Helper para dados mockados 
  // REMOVER QUANDO INTEGRAR COM O BACKEND
  private getFormattedDateForMock(offsetDays: number): string {
    const date = new Date();
    date.setDate(date.getDate() + offsetDays);
    return date.toISOString().split('T')[0];
  }


  agendar(): void {
    if (this.selectedSpecialtyId && this.selectedDoctorId && this.selectedDate && this.selectedTimeSlotId) {
      // TODO: Fazer chamada HTTP para o backend para AGENDAR a consulta
      const agendamentoPayload = {
        especialidadeId: this.selectedSpecialtyId,
        medicoId: this.selectedDoctorId,
        data: this.selectedDate.toISOString().split('T')[0], 
        horarioId: this.selectedTimeSlotId,
        // pacienteId: 
      };

      console.log('Payload de Agendamento:', agendamentoPayload);

      // POR ENQUANTO (para continuar testando o front sem backend):
      const specialty = this.specialties.find(s => s.id === this.selectedSpecialtyId);
      const mockAllDoctors = [ 
        { id: 101, nome: 'Dr(a). Ana Costa', especialidadeId: 1 },
        { id: 102, nome: 'Dr(a). Bruno Lima', especialidadeId: 2 },
      ];
      const doctor = mockAllDoctors.find(d => d.id === this.selectedDoctorId);
      const mockAllTimeSlots = [ 
        { id: 1001, hora: '09:00', data: this.getFormattedDateForMock(0), medicoId: 101, disponivel: true },
      ];
      const timeSlot = mockAllTimeSlots.find(t => t.id === this.selectedTimeSlotId);


      alert(`Consulta agendada com ${doctor?.nome} (${specialty?.nome}) em ${this.selectedDate?.toLocaleDateString()} às ${timeSlot?.hora}.`);
      this.resetForm();

    } else {
      alert('Por favor, preencha todos os campos para agendar a consulta.');
    }
  }

  // Método para resetar o formulário
  private resetForm(): void {
    this.selectedSpecialtyId = null;
    this.selectedDoctorId = null;
    this.selectedDate = null;
    this.selectedTimeSlotId = null;
    this.doctors = [];
    this.availableDates = [];
    this.availableTimeSlots = [];
    this.loadSpecialties(); 
  }

  cancel(): void {
    console.log('Ação de agendamento cancelada. Voltando para a Home.');
    this.router.navigate(['/home']);
  }
}