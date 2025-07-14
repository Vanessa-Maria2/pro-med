import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IniciarAtendimentoComponent } from './iniciar-atendimento.component';

describe('IniciarAtendimentoComponent', () => {
  let component: IniciarAtendimentoComponent;
  let fixture: ComponentFixture<IniciarAtendimentoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IniciarAtendimentoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IniciarAtendimentoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
