import { QuestionDisplayModel } from '../questions/question.models';

export interface TestDisplayModel{
    id:number,
    name:string,
    questionDisplayDto:QuestionDisplayModel[]
}

export class TestAddModel{
    constructor(public name: string, public questionsIds: number[]){}
}