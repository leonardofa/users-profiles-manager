import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ImageLoaderService} from '../image-loader.service';
import {UserService} from "../user.service";
import {User} from "../data/user";
import {Router} from "@angular/router";
import Utils from "../service/localstore.service"

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  logoUrl: string = '';

  constructor(
    private formBuilder: FormBuilder,
    private imageLoader: ImageLoaderService,
    private userService: UserService,
    private router: Router
  ) {
    this.loginForm = this.formBuilder.group({
      identification: ['', [Validators.required]],
      secret: ['', [Validators.required]]
    });
  }

  ngOnInit(): void {
    this.loadLogo();
  }

  loadLogo() {
    const logoUrl = '../../assets/login.svg';
    this.imageLoader.loadImage(logoUrl).subscribe((blob: Blob) => {
      this.logoUrl = URL.createObjectURL(blob);
    });
  }

  onSubmit(): void {
    Utils.cleanAuth();
    if (this.loginForm.valid) {
      this.userService.login(this.loginForm.value).subscribe(
        (data: User) => {
          localStorage.setItem('userId', data.identification);
          localStorage.setItem('userIdentification', data.id);
          localStorage.setItem('userProfile', data.profile);
          localStorage.setItem('token', btoa(`${data.identification}:${this.loginForm.get('secret')?.value}`));
          if(data.profile === 'ADMINISTRATOR'){
            this.router.navigateByUrl('/profile');
          }else{
            this.router.navigateByUrl('/user');
          }
        });
    } else {
      return;
    }
  }
}


