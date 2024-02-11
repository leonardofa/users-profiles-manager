import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserService} from "../user.service";
import {Router} from "@angular/router";
import Utils from "../service/localstore.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {User} from "../data/user";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  form: FormGroup;
  user!: User;

  constructor(
    private snackBar: MatSnackBar,
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService,
  ) {
    this.form = this.formBuilder.group({
      identification: ['', [Validators.required]],
      secret: ['', [Validators.required]]
    });
    const userId = localStorage.getItem('userId');
    if (userId) {
      this.userService.get(userId).subscribe(
        (data: User) => {
          this.user = data;
        });
    } else {
      this.snackBar.open('Is not possible load the user', 'Close', {
        duration: 5000,
        verticalPosition: "top"
      });
      this.logout();
    }
  }

  ngOnInit(): void {
  }

  onSubmit() {
  }

  logout() {
    Utils.cleanAuth();
    this.router.navigateByUrl('/login');
  }

}
