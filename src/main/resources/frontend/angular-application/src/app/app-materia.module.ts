import { NgModule } from '@angular/core';
import { MatMenuModule } from '@angular/material/menu';
import { MatSliderModule } from '@angular/material/slider';
import { MatDividerModule } from '@angular/material/divider';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule }from '@angular/material/card';
import { MatDialogModule }from '@angular/material/dialog';
import { MatButtonToggleModule } from '@angular/material/button-toggle'
import { CommonModule } from '@angular/common';

@NgModule({
  imports: [
    CommonModule,
    MatMenuModule,
    MatSliderModule,
    MatDividerModule,
    MatListModule,
    MatButtonModule,
    MatCardModule,
    MatDialogModule,
    MatButtonToggleModule
  ],
  exports:[
    CommonModule,
    MatMenuModule,
    MatSliderModule,
    MatDividerModule,
    MatListModule,
    MatButtonModule,
    MatCardModule,
    MatDialogModule,
    MatButtonToggleModule
  ]
})
export class AppMateriaModule { }
