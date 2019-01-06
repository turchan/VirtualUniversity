export class CreateUserInfo {
  login: string;
  password: string;
  name: string;
  surname: string;
  country: string;
  email: string;
  city: string;
  role_id: string[];


  constructor(login: string, password: string, name: string, surname: string, country: string, email: string, city: string) {
    this.login = login;
    this.password = password;
    this.name = name;
    this.surname = surname;
    this.country = country;
    this.email = email;
    this.city = city;
    this.role_id = ['ROLE_USER'];
  }
}
