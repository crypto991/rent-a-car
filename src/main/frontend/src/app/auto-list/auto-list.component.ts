import {Component, OnInit} from '@angular/core';
import {AutoDataService} from "../service/auto-data.service";
import {Automobil} from "../classes/automobil";
import {Router} from "@angular/router";
import {Najam} from "../classes/najam";

@Component({
  selector: 'app-auto-list',
  templateUrl: './auto-list.component.html',
  styleUrls: ['./auto-list.component.css']
})
export class AutoListComponent implements OnInit {

  automobili: Automobil[];
  automobiliItems: Automobil[];

  automobil: Automobil;
  najam : Najam;

  config: any;

  title = 'app';
  employees: Array<any>;
  totalRec : number;


  pageNum: number = 0;
  totalElements;
  totalPages;


  constructor(private service : AutoDataService,
              private router : Router) {
      this.totalElements = 0;
      this.totalPages = 0;
    // this.config = {
    //   itemsPerPage: 5,
    //   currentPage: 1,
    //   totalItems: this.totalElements
    // };

  }




  ngOnInit() {

    this.service.find('','','',this.pageNum).subscribe(response =>{
      this.automobili = response.body;
      this.totalElements = response.headers.get('totalElements')
      this.totalPages = response.headers.get('totalPages')

    });

  }



  nazad(){
    if(this.pageNum > 0){
      this.pageNum = this.pageNum - 1;
      this.service.find('','','',this.pageNum).subscribe(res =>{
        this.automobili = res.body;
      });
    }
  }

  napred (){
    if(this.pageNum < this.totalPages - 1){
      this.pageNum = this.pageNum + 1;
      this.service.find(''  , '' , '' ,this.pageNum).subscribe(data=> {
        this.automobili = data.body
        // console.log(this.automobili)
      });

    }
  }

  iznajmi(id){
    this.service.iznajmi(id).subscribe();
    this.refreshAuto();
  }


  delete(id) {
    this.service.delete(id).subscribe(result=> {
      this.refreshAuto();
    });
  }

  update(id){
    this.service.getById(id).subscribe(
      result => this.router.navigate(['/home', id])
    )
  }

  refreshAuto(){
    this.service.findAll().subscribe(data => {
      this.automobili = data.body;
    });
  }

}
