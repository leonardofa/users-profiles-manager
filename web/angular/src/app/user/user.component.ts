import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {UserService} from "../user.service";
import {Router} from "@angular/router";
import Utils from "../service/localstore.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {User} from "../data/user";
import {DateAdapter, MAT_DATE_LOCALE} from "@angular/material/core";
import {MatDatepickerIntl} from "@angular/material/datepicker";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
  providers: [
    {provide: MAT_DATE_LOCALE, useValue: 'pr-BR'},
  ],
})
export class UserComponent implements OnInit {
  form: FormGroup;
  user!: User;
  isNew:boolean = false;

  constructor(
    private snackBar: MatSnackBar,
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService,
    private _adapter: DateAdapter<any>,
    private _intl: MatDatepickerIntl,
  ) {
    this._adapter.setLocale("pt");
    this._intl.changes.next();
    this.form = this.formBuilder.group({
      identification: new FormControl('', [Validators.required]),
      secret: new FormControl('', [Validators.required]),
      name: new FormControl('', [Validators.required]),
      born: new FormControl('',),
      contact: new FormControl(''),
      profile: new FormControl('')
    });
    const userId = localStorage.getItem('userId');
    if (userId) {
      this.isNew = false;
      this.userService.get(userId).subscribe(
        (data: User) => {
          this.user = data;
          this.form = this.formBuilder.group({
            name: new FormControl(this.user.name, [Validators.required]),
            born: new FormControl(this.user.born),
            contact: new FormControl(this.user.contact),
            profile: new FormControl(this.user.profile)
          });
          this.form.get('profile')?.disable();
        });
    } else {
      this.isNew = true;
      this.user = {born: "", contact: "", id: "", identification: "", name: "", profile: ""};
    }
  }

  ngOnInit(): void {
  }

  onSubmit() {
    if (this.form.valid) {
      if(this.isNew){
        this.userService.create(this.form.value).subscribe(
          (data: User) => {
            this.router.navigateByUrl('/login');
            this.snackBar.open(`User ${data.identification} created`, 'Close', {
              duration: 5000,
              verticalPosition: "top",
            });
          });
      }else{
        this.userService.update(this.user.id, this.form.value).subscribe(
          (data: User) => {
            this.snackBar.open(`User ${data.identification} updated`, 'Close', {
              duration: 5000,
              verticalPosition: "top",
            });
          });
      }
    } else {
      return;
    }
  }

  logout() {
    Utils.cleanAuth();
    this.router.navigateByUrl('/login');
  }

  protected readonly localStorage = localStorage;

  title() {
    return this.isNew ? 'SignUp': ('User ' + this.user?.identification);
  }
}
