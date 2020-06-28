import { NgModule } from '@angular/core';
import { MatMenuModule } from '@angular/material/menu';
import { MatSliderModule } from '@angular/material/slider';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule }from '@angular/material/card';
import { MatButtonToggleModule } from '@angular/material/button-toggle'
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatCheckboxModule } from '@angular/material/checkbox';


const impexp = [
  CommonModule,
  MatInputModule,
  MatMenuModule,
  MatSliderModule,
  MatListModule,
  MatButtonModule,
  MatCardModule,
  MatButtonToggleModule,
  MatTableModule,
  MatCheckboxModule
]

@NgModule({
  imports: [
    impexp
  ],
  exports:[
    impexp
  ]
})
export class AppMateriaModule { }
