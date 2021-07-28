import { IPropositions } from 'app/shared/model/propositions.model';

export interface IQuestions {
  id?: number;
  idquestion?: number;
  question?: string;
  iDQUESTIONS?: IPropositions[];
}

export const defaultValue: Readonly<IQuestions> = {};
