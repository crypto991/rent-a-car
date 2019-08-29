import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Automobil} from "../classes/automobil";
import {AutoDataService} from "../service/auto-data.service";

@Component({
  selector: 'app-searchresult',
  templateUrl: './searchresult.component.html',
  styleUrls: ['./searchresult.component.css']
})
export class SearchresultComponent implements OnInit {

  automobili: Automobil[];
  automobil: Automobil;

  constructor(private route : ActivatedRoute, private router: Router,
              private service: AutoDataService) { }

  ngOnInit() {

  }

  search(){
    // this.service.find(this.automobil.model, this.automobil.godiste, this.automobil.potrosnja).subscribe(
    //   result => {
    //     this.automobili = result;
    //     console.log(this.automobili);
    //   }
    // )
  }

}
