// frontend/src/app/services/user.service.ts
import { Injectable, PLATFORM_ID, Inject } from '@angular/core'; 
import { isPlatformBrowser } from '@angular/common'; 
import { BehaviorSubject, Observable } from 'rxjs';
import { PessoaType } from '../models/pessoaType';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private currentUserSubject: BehaviorSubject<PessoaType | null>;
  public currentUser: Observable<PessoaType | null>;

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {
    let storedUser: PessoaType | null = null;
    if (isPlatformBrowser(this.platformId)) {
      const user = localStorage.getItem('loggedInUser');
      if (user) {
        storedUser = JSON.parse(user);
      }
    }
    this.currentUserSubject = new BehaviorSubject<PessoaType | null>(storedUser);
    this.currentUser = this.currentUserSubject.asObservable();
  }

  setLoggedInUser(user: PessoaType): void {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.setItem('loggedInUser', JSON.stringify(user));
    }
    this.currentUserSubject.next(user);
  }

  getLoggedInUser(): PessoaType | null {
    return this.currentUserSubject.value;
  }

  logout(): void {
    if (isPlatformBrowser(this.platformId)) {
      console.log("navegador")
      localStorage.removeItem('loggedInUser');
    }
    this.currentUserSubject.next(null);
  }
}