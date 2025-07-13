// frontend/src/app/services/user.service.ts
import { Injectable, PLATFORM_ID, Inject } from '@angular/core'; 
import { isPlatformBrowser } from '@angular/common'; 
import { BehaviorSubject, Observable } from 'rxjs';

export interface LoggedInUser {
  nome: string;
  tipo: string;
}

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private currentUserSubject: BehaviorSubject<LoggedInUser | null>;
  public currentUser: Observable<LoggedInUser | null>;

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {
    let storedUser: LoggedInUser | null = null;
    if (isPlatformBrowser(this.platformId)) {
      const user = localStorage.getItem('loggedInUser');
      if (user) {
        storedUser = JSON.parse(user);
      }
    }
    this.currentUserSubject = new BehaviorSubject<LoggedInUser | null>(storedUser);
    this.currentUser = this.currentUserSubject.asObservable();
  }

  setLoggedInUser(user: LoggedInUser): void {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.setItem('loggedInUser', JSON.stringify(user));
    }
    this.currentUserSubject.next(user);
  }

  getLoggedInUser(): LoggedInUser | null {
    return this.currentUserSubject.value;
  }

  logout(): void {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.removeItem('loggedInUser');
    }
    this.currentUserSubject.next(null);
  }
}