import { Injectable } from '@angular/core';
import { Role }       from '../model/role';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  constructor()
  {
    console.log("Role Service");
  }

  getRoles(): Object[]
  {
    return Object.keys(Role).map(key => ({id: Role[key], name: key}));
  }
}
