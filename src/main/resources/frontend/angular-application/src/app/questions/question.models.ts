export interface QuestionDisplayModel{
    id:number,
    content:string,
    testsIds:number[],
    answers:AnswerDisplayModel[]
}

export interface AnswerDisplayModel{
    id:number,
    content:string
}

export class QuestionCreateModel{
    constructor(public content:string, public answers: AnswerCreateModel[]){};
}

export class AnswerCreateModel{
    constructor(public correct:boolean, public content:string){};
}