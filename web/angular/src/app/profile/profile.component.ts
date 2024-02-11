import {Component, OnInit} from '@angular/core';
import Utils from "../service/localstore.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  form: FormGroup;

  constructor(
    private snackBar: MatSnackBar,
    private formBuilder: FormBuilder,
    private router: Router
  ) {
    this.form = this.formBuilder.group({
      identification: ['', [Validators.required]],
      secret: ['', [Validators.required]]
    });
  }

  ngOnInit(): void {
    console.log(localStorage.getItem('profile'));
    if (localStorage.getItem('profile') !== 'ADMINISTRATOR') {
      this.snackBar.open('User does not allow to see this page', 'Close', {
        duration: 5000,
        verticalPosition: "top"
      });
      this.logout();
    }
  }

  logout() {
    Utils.cleanAuth();
    this.router.navigateByUrl('/login');
  }

  onSubmit() {
  }

}
