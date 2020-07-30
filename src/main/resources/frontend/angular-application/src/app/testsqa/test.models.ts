import { QuestionDisplayModel, QuestionDisplaySolveModel, QuestionSolveModel, QuestionSolveInfoModel } from '../questions/question.models';

export interface TestDisplayModel{
    id:number,
    name:string,
    questionDisplayDto:QuestionDisplayModel[]
}

export interface TestDisplaySolveModel{
    id:number,
    name:string,
    questionDisplayDto:QuestionDisplaySolveModel[]
}

export interface TestShareDisplayModel{
    name:string,
    shareUrl:string
}

export class TestAddModel{
    constructor(public name: string, public questionsIds: number[]){}
}

export class ShareTestModel{
    constructor(public id: number, public points: number){}
}

export class TestSoveModel{
    constructor(public name:string ,public id:number, public questions:QuestionSolveModel[]){}
}

export interface TestSoveInfoModel{
    name:string,
    userName:string,
    questionSolved:QuestionSolveInfoModel[]
}