export interface QuestionDisplayModel{
    id:number,
    content:string,
    testsIds:number[],
    answers:AnswerDisplayModel[]
}

export interface AnswerDisplayModel{
    id:number,
    content:string,
    correct:boolean
}

export interface QuestionDisplaySolveModel{
    id:number,
    content:string,
    testsIds:number[],
    answers:AnswerDisplaySolveModel[]
}

export class QuestionSolveModel{
    constructor(public id:number, public answers: AnswerSolveModel[]){}
}

export interface AnswerDisplaySolveModel{
    id:number,
    content:string
}

export class QuestionCreateModel{
    constructor(public content:string, public answers: AnswerCreateModel[]){};
}

export class QuestionEditModel{
    constructor(public content:string, public answerEditDto: AnswerEditModel[], public answerCreateDto: AnswerCreateModel[]){};
}

export class AnswerCreateModel{
    constructor(public correct:boolean, public content:string){};
}

export class AnswerEditModel{
    constructor(public id:number, public correct:boolean, public content:string){};
}

export class AnswerSolveModel{
    constructor(public id:number, public correct:boolean){}
}