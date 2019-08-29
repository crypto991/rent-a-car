import {Component, OnInit} from '@angular/core';
import {Automobil} from "../classes/automobil";
import {AutoDataService} from "../service/auto-data.service";
import {ActivatedRoute, Router} from "@angular/router";


@Component({
  selector: 'app-auto-search',
  templateUrl: './auto-search.component.html',
  styleUrls: ['./auto-search.component.css']
})
export class AutoSearchComponent implements OnInit {

  model : string = '';
  godiste: string = '';
  potrosnja: string = '';
  pageNum: number = 0;
  totalPages;

  automobili : Automobil[] = [];
  config: any;

  constructor(private router: Router, private service: AutoDataService,
              private route: ActivatedRoute) {
  this.totalPages = 0;
  }

  ngOnInit() {
    // this.router.navigate(['result', {model:width,height:height}]);

  }



  search() {
    // let params = new HttpParams().set("model", this.automobil.model).
    // set("godiste", this.automobil.godiste).set("potrosnja", this.automobil.potrosnja);
    this.service.find(this.model, this.godiste, this.potrosnja, this.pageNum).subscribe(
      result => {
        this.automobili = result.body;
        this.totalPages = result.headers.get('totalPages');
        console.log(result.body)
      }
    )

  }

  nazad(){
    if(this.pageNum > 0){
      this.pageNum = this.pageNum - 1;
      this.service.find(this.model, this.godiste, this.potrosnja,this.pageNum).subscribe(res =>{
        this.automobili = res.body;
      });
    }
  }

  napred(){
    if(this.pageNum < this.totalPages - 1){
      this.pageNum = this.pageNum + 1;
      this.service.find(this.model, this.godiste, this.potrosnja,this.pageNum).subscribe(data=> {
        this.automobili = data.body
        // console.log(this.automobili)
      });

    }
  }




}
