import {Component, OnInit} from '@angular/core';
import {Automobil} from "../classes/automobil";
import {ActivatedRoute, Router} from "@angular/router";
import {AutoDataService} from "../service/auto-data.service";
import {KompDataService} from "../service/komp-data.service";
import {Kompanija} from "../classes/kompanija";

@Component({
  selector: 'app-auto-form',
  templateUrl: './auto-form.component.html',
  styleUrls: ['./auto-form.component.css']
})
export class AutoFormComponent implements OnInit {

  automobil: Automobil;
  kompanije: Kompanija[];
  id:number;

  constructor(private router: Router, private service: AutoDataService,
              private serviceKomp : KompDataService, private route: ActivatedRoute) {
    this.automobil = new Automobil();
  }

  ngOnInit() {
    this.serviceKomp.findAll().subscribe(data => {
      this.kompanije = data;
    });
    this.id = this.route.snapshot.params['id'];

    console.log(this.id)

    if(this.id!=undefined) {
      this.service.getById(this.id)
        .subscribe (
          data => {
            this.automobil = data
            console.log(this.automobil)
          }
        )
    }
  }

  onSubmit() {
    if(this.id == undefined) {
        this.service.save(this.automobil).subscribe(result => this.gotoAutomobilList());
    }else{
        this.service.update(this.id, this.automobil).subscribe(result=> this.gotoAutomobilList());
    }
  }

  gotoAutomobilList() {
    this.router.navigate(['/automobili']);
  }
}
