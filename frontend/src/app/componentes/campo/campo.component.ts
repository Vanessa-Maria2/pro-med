import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatTooltipModule } from '@angular/material/tooltip';
import { NgxMaskDirective } from 'ngx-mask';

@Component({
  selector: 'app-campo',
  imports: [CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatTooltipModule,
    NgxMaskDirective
  ],
  templateUrl: './campo.component.html',
  styleUrl: './campo.component.css'
})
export class CampoComponent {
  @Input() itens:{ descricao: string }[] = [];
  @Output() itensChange = new EventEmitter<{ descricao: string }[]>();
  @Input() mask?: string;

  @Input() nomeCampo = 'Item';

  adicionar(): void {
    this.itens.push({ descricao: '' });
    this.emitirMudanca();
  }

  remover(index: number): void {
    this.itens.splice(index, 1);
    this.emitirMudanca();
  }

  atualizarDescricao(index: number, novaDescricao: string): void {
    this.itens[index].descricao = novaDescricao;
    this.emitirMudanca();
  }

  private emitirMudanca(): void {
    this.itensChange.emit(this.itens);
  }
}
