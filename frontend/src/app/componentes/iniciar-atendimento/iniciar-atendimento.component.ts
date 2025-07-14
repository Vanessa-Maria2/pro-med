// frontend/src/app/pages/iniciar-atendimento/iniciar-atendimento.component.ts
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon'; 
import { Router } from '@angular/router';
import { PessoaType } from '../../models/pessoaType';
import { UserService } from '../../services/user.service';

interface Medicamento {
  id: number;
  nome: string;
}

interface PrescricaoItem {
  medicamentoId: number | null;
  dosagem: string; 
  instrucoes: string; 
}

interface ExameTipo {
  id: number;
  descricao: string;
}

@Component({
  selector: 'app-iniciar-atendimento',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatCardModule,
    MatIconModule 
  ],
  templateUrl: './iniciar-atendimento.component.html',
  styleUrl: './iniciar-atendimento.component.css'
})
export class IniciarAtendimentoComponent implements OnInit {
  user: PessoaType | null = null;

  // Dados do formulário
  consultationSummary: string = '';
  prescriptionItems: PrescricaoItem[] = [{ medicamentoId: null, dosagem: '', instrucoes: '' }]; // Começa com 1 item vazio
  selectedExamTypeIds: number[] = []; 

  // Dados mockados para selects
  allMedicines: Medicamento[] = [
    { id: 1, nome: 'Paracetamol' },
    { id: 2, nome: 'Amoxicilina' },
    { id: 3, nome: 'Ibuprofeno' },
    { id: 4, nome: 'Dipirona' },
  ];

  allExamTypes: ExameTipo[] = [
    { id: 101, descricao: 'Hemograma Completo' },
    { id: 102, descricao: 'Exame de Urina' },
    { id: 103, descricao: 'Raio-X de Tórax' },
    { id: 104, descricao: 'Eletrocardiograma' },
  ];

    get isAtendimentoFormInvalid(): boolean {
    let invalid = false; 

    // 1. Valida se o resumo da consulta está vazio ou só tem espaços
    if (!this.consultationSummary || this.consultationSummary.trim() === '') {
      invalid = true;
    }

    // 2. Validação dos itens da receita (somente se a validação do resumo passou)
    if (!invalid) { 
      const hasIncompletePrescriptionItem = this.prescriptionItems.some(item =>
        !item.medicamentoId ||
        !item.dosagem || item.dosagem.trim() === '' ||
        !item.instrucoes || item.instrucoes.trim() === ''
      );

      if (hasIncompletePrescriptionItem) {
        invalid = true;
      }
    }


    return invalid; 
  }

  constructor(private router: Router, private userService: UserService) {
    this.user = this.userService.getLoggedInUser();
  }

  ngOnInit(): void {
    
  }
  
  addPrescriptionItem(): void {
    this.prescriptionItems.push({ medicamentoId: null, dosagem: '', instrucoes: '' });
  }

  removePrescriptionItem(index: number): void {
    this.prescriptionItems.splice(index, 1);
  }

  
  solicitarAtendimento(): void {
    console.log('Resumo da Consulta:', this.consultationSummary);
    console.log('Itens da Prescrição:', this.prescriptionItems);
    console.log('Exames Solicitados IDs:', this.selectedExamTypeIds);

    // TODO: Enviar esses dados para o backend
    alert('Atendimento registrado com sucesso! (Dados no console)');

    // Resetar o formulário 
    this.consultationSummary = '';
    this.prescriptionItems = [{ medicamentoId: null, dosagem: '', instrucoes: '' }];
    this.selectedExamTypeIds = [];
  }
  
  // Retornar para a Home (ou dashboard do médico)
  cancel(): void {
    console.log('Atendimento cancelado. Voltando para o dashboard do médico.');
    this.router.navigate(['/home/medico']); 
  }
}