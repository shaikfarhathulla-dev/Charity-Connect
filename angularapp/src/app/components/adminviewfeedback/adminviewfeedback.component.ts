import { Component, OnInit } from '@angular/core';
import { Feedback } from 'src/app/models/feedback.model';
import { FeedbackService } from 'src/app/services/feedback.service';

@Component({
  selector: 'app-adminviewfeedback',
  templateUrl: './adminviewfeedback.component.html',
  styleUrls: ['./adminviewfeedback.component.css']
}) 
export class AdminviewfeedbackComponent implements OnInit {
  feedbacks:Feedback[]=[];
  selectedFeedback:Feedback=null;
  flag:boolean=true;
  isLoading:boolean = true;
  constructor(private feedbackService:FeedbackService) { }

  ngOnInit(): void {
    this.getFeedbacks();
    
  }

  public getFeedbacks(){
    return this.feedbackService.getFeedbacks().subscribe(data=>
      {this.feedbacks=data;
        console.log(JSON.stringify(this.feedbacks));
        this.isLoading =false;
    });
  }

  public showProfile(fb:Feedback){
    this.selectedFeedback=fb;
  }

}
